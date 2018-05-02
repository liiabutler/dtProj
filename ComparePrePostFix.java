import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ComparePrePostFix {
    private static List<File> alreadyConsistent = new ArrayList<File>();
    private static List<File> nowConsistent = new ArrayList<File>();
    private static List<File> stillInconsistent = new ArrayList<File>();
    private static int numAlreadyConsistent = 0;
    private static int numNowConsistent = 0;
    private static int numStillInconsistent = 0;


    public static void main(String[] args){
        // list to parse the arguments
        List<String> argsList = new ArrayList<String>(Arrays.asList(args));

        // get directory for the input of text files
        int preFolderIndex = argsList.indexOf("-prefix");
        File folder = null;
        File[] preFiles = null;
        if (preFolderIndex != -1) {
            int preFolderNameIndex = preFolderIndex+1;
            if (preFolderNameIndex >= argsList.size()){
                System.err.println("Pre-fix folder name not found. Please use format: -prefix /path/to/prefix/files");
                System.exit(0);
            }
            folder = new File(argsList.get(preFolderNameIndex));
            preFiles = folder.listFiles();
            for(int i=0; i < preFiles.length; i++){
                if(!preFiles[i].isFile()) {
                    System.err.println("Invalid file found.");
                    System.exit(0);
                }
            }
            //put more error check here
        }
        int postFolderIndex = argsList.indexOf("-postfix");
        File folder2 = null;
        File[] postFiles = null;
        if (postFolderIndex != -1){
            int postFolderNameIndex = postFolderIndex+1;
            if (postFolderNameIndex >= argsList.size()){
                System.err.println("Pre-fix folder name not found. Please use format: -prefix /path/to/prefix/files");
                System.exit(0);
            }
            folder = new File(argsList.get(postFolderNameIndex));
            postFiles = folder.listFiles();
            for(int i=0; i < postFiles.length; i++){
                if(!postFiles[i].isFile()) {
                    System.err.println("Invalid file found.");
                    System.exit(0);
                }
            }
            //put more error check here

        }
        Map<File,File> pairs = pairTests(preFiles, postFiles);
        extractInfo(pairs);



        //summary
        System.out.println("Already consistent: "+numAlreadyConsistent);
        System.out.println("Now consistent: " + numNowConsistent);
        System.out.println("Total consistent: " + (numAlreadyConsistent+numNowConsistent));
        System.out.println("Still inconsistent: "+ numStillInconsistent);
        System.out.println("");

        //
        System.out.println("Files now consistent:");
        for(int i=0; i<nowConsistent.size();i++){
            System.out.println(nowConsistent.get(i).getName());
        }
        System.out.println("");
        System.out.println("Files still inconsistent:");
        for(int i=0; i<stillInconsistent.size();i++){
            System.out.println(stillInconsistent.get(i).getName());
        }

    }

    //right now this just creates an even pairing and will ignore files that may have only existed in pre or post
    public static Map<File,File> pairTests(File[] preFiles, File[] postFiles){
        //ADD LOGIC SO THAT IF YOU REMOVE FILES
        //List<File> prefileList = new ArrayList<File>(Arrays.asList(preFiles));
        //List<File> postfileList = new ArrayList<File>(Arrays.asList(postFiles));
        Map<File, File> fixPairs = new HashMap<File,File>();

        for(int i=0; i < preFiles.length; i++){
            //boolean isPaired = false;
            for(int j=0; j<postFiles.length;j++) {
                if (preFiles[i].getName().equals(postFiles[j].getName())) {
                    //isPaired = true;
                    fixPairs.put(preFiles[i], postFiles[j]);
                    break;
                }
            }
        }
        return fixPairs;
    }
    private static void extractInfo(Map<File,File> pairs){
        Object[] preFileArr = pairs.keySet().toArray();
        Object[] postFileArr = pairs.values().toArray();
        for(int i=0; i<pairs.size(); i++){

            processFilePair((File) preFileArr[i], (File)postFileArr[i]);
        }

    }
    private static void processFilePair(File preFile, File postFile){
        BufferedReader brPre;
        BufferedReader brPost;
        try {
            brPre = new BufferedReader(new FileReader(preFile));
            brPost = new BufferedReader(new FileReader(postFile));
            String sPre = brPre.readLine();
            String sPost = brPost.readLine();
            //System.out.println(sPre);
            //System.out.println(sPost);
            //System.out.println("");
            int preInconsistentIdx = sPre.indexOf(':') + 2;
            int postInconsistentIdx = sPost.indexOf(':') + 2;

            int preInconsistent = Integer.parseInt(sPre.substring(preInconsistentIdx));
            int postInconsistent = Integer.parseInt(sPost.substring(postInconsistentIdx));

            if(preInconsistent == 0 && postInconsistent == 0){
                numAlreadyConsistent++;
                alreadyConsistent.add(postFile);

            } else if (preInconsistent > 0 && postInconsistent == 0){
                numNowConsistent++;
                nowConsistent.add(postFile);
            } else {
                numStillInconsistent++;
                stillInconsistent.add(postFile);
            }


            brPre.close();
            brPost.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }  catch (IOException e){
            e.printStackTrace();
        }


    }


}



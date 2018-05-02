import sys

def main():
	with open('dt-list.txt') as f:
		allTests = f.readlines()
	allTests = [x.strip() for x in allTests]

	for x in allTests:
		print ("Test: " + x)
		print ""
		print "Observations ran all together: "
		print ""
		print "Observations ran in dependent test order: "
		print ""
		print "Conclusion: "
		print ""
if __name__ == '__main__':
	main()

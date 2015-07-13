
readFile = open("Challenges.txt", "r")
fileText = readFile.read()
readFile.close()

lines = fileText.split("\n")
for i in range(len(lines)):
    id = lines[i].split("\t")[1]
    name = lines[i].split("\t")[0]

    writeFile = open("Text.txt", "a")
    writeFile.write(id + "\t" + name + "\n")
writeFile.close()

# java-kindle-to-anki
A java program that converts kindle highlights html to anki csv file for importing
 

# How to run
Note:  minimum jdk (not just jre) version: 1.8

```bash
mvn clean package 
unzip target/kindle-to-anki*jarset.zip -d /path/to/your/dir
cd /path/to/your/dir/kindle-to-anki*
./k2a.sh #Just to see the arguments
./k2a.sh /path/to/your/kindleHighlights.html  
# or k2a.bat for windows
``` 

After that you will get a csv file.  Import it into your Anki application. 
You will see new cards created:  
* the front of a card is a highlight
* the back is empty

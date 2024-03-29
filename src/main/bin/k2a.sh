#!/bin/sh  
  
PRG="$0"    
    
# resolve links - $0 may be a softlink    
while [ -h "$PRG" ]; do    
  ls=`ls -ld "$PRG"`    
  link=`expr "$ls" : '.*-> \(.*\)$'`    
  if expr "$link" : '/.*' > /dev/null; then    
    PRG="$link"    
  else    
    PRG=`dirname "$PRG"`/"$link"    
  fi    
done    
    
    
PRGDIR=`dirname "$PRG"`      
  
  
java -cp $(echo $PRGDIR/jars/*.jar | tr ' ' ':') com.chenjianjx.k2a.ui.Kindle2AnkiMain $*
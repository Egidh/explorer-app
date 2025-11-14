#! /bin/bash
javac -d build/ $(find "./src" -name "*.java") && java -cp build/ com.esiea.pootd2.ExplorerApp

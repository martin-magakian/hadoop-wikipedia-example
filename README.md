Hadoop example using wikipedia dump: finding the links
============

This Hadoop project find all the link in Wikipedia. It parse parse the full wikipedia <b>100 Go</b> dump XML.

Requirement
---------
- Hadoop up and running


How to use
=======

1- Import dump simple
------
A wikipedia dump sample is available in the /wikidump folder. Unzip it and import the xml into hadoop.

	$ cd wikidump
	$ unzip wikidump_sample.xml.zip
	$ hadoop fs -mkdir hadoop_sample
	$ hadoop fs -copyFromLocal wikidump_sample.xml hadoop_sample/


2- Compile the hadoop project
------
	$ cd ../
	$ mvn clean install
	

3- Run the hadoop wikipedia link finder
------
	$ hadoop jar today/target/today-1-jar-with-dependencies.jar 'hadoop_sample' 'hadoop_sample_result'

4- Download the result
------
	$ hadoop fs -copyToLocal hadoop_sample_result hadoop_sample_result
	$ vim hadoop_sample_result/part-r-00000
	
	
Looking at the result
------

	PageName      | TotalLink |   (LinkType|LinkPage)     |     (LinkType|LinkPage)    |   (LinkType|LinkPage) 

	Data_register |    1      | 0|Atomic_semantics        |                            |
	Demographics  |    3      | 0|Demographics_of_Armenia | 0|Demographics_of_American | 0|Demographics_of_Angola
	Kevin_Gilbert |    1      | 1|Talk%3AAutoerotic       |

All the LinkType listed on <a href="http://en.wikipedia.org/wiki/Wikipedia:Namespace#Programming">wikipedia</a>



Contact
=========
Developed by Martin Magakian dev.martin.magakian@gmail.com

For [doduck prototype](http://doduck.com/) <br />
Pour [doduck prototype](http://doduck.fr/) (site en Francais)<br />


License
=========
MIT License (MIT)

![githalytics.com alpha](https://cruel-carlota.gopagoda.com/40547d77def851fb3c05e23989ea29cd "githalytics.com")


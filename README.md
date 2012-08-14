# Test Task for HireRight

Solution is based on the sample code from official SAX tutorial page [http://docs.oracle.com/javase/tutorial/jaxp/sax/index.html](http://docs.oracle.com/javase/tutorial/jaxp/sax/index.html)

To get the program run you will require Git, Java SDK and Maven to be installed on you computer.
Originally were used Java 7 SDK upd5, Maven 3.0.3 and Git 1.7.11.
Executing the commands below will pull down sources, build them and print out usage instructions.

	git clone https://github.com/apetuhhov/testtask_hireright.git
	cd testtask_hireright
	mvn clean install
	cd target
	java -jar testtask_hireright.jar -usage
	
Usage printout:
>Usage: ElemCount [-options] &lt;file.xml>  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-dtd = DTD validation  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-xsd | -xsdss &lt;file.xsd> = W3C XML Schema validation using xsi: hints  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;in instance document or schema source &lt;file.xsd>  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-xsdss &lt;file> = W3C XML Schema validation using schema source &lt;file>  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-e &lt;name...> = comma separated list of element names (local or qalified), whose occurences to be count  
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-usage or -help = this message  

Next commands will run tests used while writing the code:

	java -jar testtask_hireright.jar -e name,item -xsdss test-classes/PurchaseOrder/po.xsd test-classes/PurchaseOrder/po.xml
	java -jar testtask_hireright.jar -e Book -xsd test-classes/PublicationCatalogue/Catalogue.xml
	java -jar testtask_hireright.jar -e state -dtd test-classes/Invoice/Invoice.xml
	java -jar testtask_hireright.jar -e FirstName -xsd test-classes/GolfCountryClub/GolfCountryClub.xml

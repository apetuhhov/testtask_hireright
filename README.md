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
>Usage: ElemCount [-options] <file.xml>  
       -dtd = DTD validation  
       -xsd | -xsdss <file.xsd> = W3C XML Schema validation using xsi: hints  
       &nbsp;&nbsp;&nbsp;&nbsp;in instance document or schema source <file.xsd>  
       -xsdss <file> = W3C XML Schema validation using schema source <file>  
       -usage or -help = this message  

Next commands will run tests used while writing the code:

	java -jar testtask_hireright.jar test-classes/PurchaseOrder/po.xml
	java -jar testtask_hireright.jar -xsdss test-classes/PurchaseOrder/po.xsd test-classes/PurchaseOrder/po.xml
	java -jar testtask_hireright.jar test-classes/PublicationCatalogue/Catalogue.xml
	java -jar testtask_hireright.jar -xsd test-classes/PublicationCatalogue/Catalogue.xml
	java -jar testtask_hireright.jar test-classes/Invoice/Invoice.xml
	java -jar testtask_hireright.jar -dtd test-classes/Invoice/Invoice.xml
	java -jar testtask_hireright.jar test-classes/GolfCountryClub/GolfCountryClub.xml
	java -jar testtask_hireright.jar -xsd test-classes/GolfCountryClub/GolfCountryClub.xml

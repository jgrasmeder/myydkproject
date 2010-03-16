package ydk.epub.maker
{
	import flash.utils.ByteArray;
	
	import ydk.zip.*;

	public class EpubMaker
	{
		public function EpubMaker()
		{
		}
		
		var private _bookname:String = "undefined.epub";
		var private _zip:ZipFile; 
		var private _mimetype:String = "application/epub+zip";
		var private _containerXml:XML = 
			<container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
			  <rootfiles>
			    <rootfile full-path="OEBPS/As You Like It.opf"
			              media-type="application/oebps-package+xml" />
			    <rootfile full-path="OEBPS/As You Like It.pdf"
			              media-type="application/pdf" />
			  </rootfiles>
			</container>;
		var private _signaturesXml:XML = 
			<signatures xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
			  <Signature Id="AsYouLikeItSignature" xmlns="http://www.w3.org/2000/09/xmldsig#">
			
				<!-- SignedInfo is the information that is actually signed. In this case -->
				<!-- the SHA1 algorithm is used to sign the canonical form of the XML    -->
				<!-- documents enumerated in the Object element below                    -->
				<SignedInfo>
				  <CanonicalizationMethod
				   Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
			
				  <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
			
				  <Reference URI="#AsYouLikeIt">
			
					<DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
			
					<DigestValue>j6lwx3rvEPO0vKtMup4NbeVu8nk=</DigestValue>
				  </Reference>
				</SignedInfo>
			
				<!-- The signed value of the digest above using the DSA algorithm -->
			
				<SignatureValue>MC0CFFrVLtRlk=...</SignatureValue>
			
				<!-- The key to use to validate the signature -->
				<KeyInfo>
				  <KeyValue>
					<DSAKeyValue>
					  <P>...</P><Q>...</Q><G>...</G><Y>...</Y>
					</DSAKeyValue>
				  </KeyValue>
				</KeyInfo>
			
				<!-- The list documents to sign. Note that the canonical form of XML   -->
				<!-- documents is signed while the binary form of the other documents -->
				<!-- is used -->
				<Object>
				  <Manifest Id="AsYouLikeIt">
					<Reference URI="OEBPS/As You Like It.opf">
					  <Transforms>
						<Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
					  </Transforms>
					</Reference>
					<Reference URI="OEBPS/book.html">
					  <Transforms>
						<Transform Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315"/>
					  </Transforms>
					</Reference>
					<Reference URI="OEBPS/images/cover.png" />
					<Reference URI="PDF/As You Like It.pdf" />
				  </Manifest>
				</Object>
			
			  </Signature>
			</signatures>;
		var private _encryption:XML = 
			<encryption
			 xmlns="urn:oasis:names:tc:opendocument:xmlns:container"
			 xmlns:enc="http://www.w3.org/2001/04/xmlenc#"
			 xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
			
			  <-- The RSA encrypted AES-128 symmetric key used to encrypt the data -->
			  <enc:EncryptedKey Id="EK”>
				<enc:EncryptionMethod  Algorithm="http://www.w3.org/2001/04/xmlenc#rsa-1_5"/>
				<ds:KeyInfo>
				  <ds:KeyName>John Smith</ds:KeyName>
				</ds:KeyInfo>
				<enc:CipherData>
				  <enc:CipherValue>xyzabc...</enc:CipherValue>
				</enc:CipherData>
			  </enc:EncryptedKey>
			
			  <!-- Each EncryptedData block identifies a single document that has been    -->
			  <!-- encrypted using the AES-128 algorithm. The data remains stored in it’s -->
			  <!-- encrypted form in the original file within the container.              -->
			  <enc:EncryptedData Id="ED1">
			
				<enc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#kw-aes128"/>
			
				<ds:KeyInfo>
			
				  <ds:RetrievalMethod URI="#EK"
			
				   Type="http://www.w3.org/2001/04/xmlenc#EncryptedKey"/>
			
				</ds:KeyInfo>
			
				<enc:CipherData>
			
				  <enc:CipherReference URI="OEBPS/book.html"/>
			
				</enc:CipherData>
			
			  </enc:EncryptedData>
			
			
			  <enc:EncryptedData Id="ED2">
			
				<enc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#kw-aes128"/>
			
				<ds:KeyInfo>
			
				  <ds:RetrievalMethod URI="#EK"
			
				   Type="http://www.w3.org/2001/04/xmlenc#EncryptedKey"/>
			
				</ds:KeyInfo>
			
				<enc:CipherData>
			
				  <enc:CipherReference URI="OEBPS/images/cover.png"/>
			
				</enc:CipherData>
			
			  </enc:EncryptedData>
			
			
			  <enc:EncryptedData Id="ED3">
			
				<enc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#kw-aes128"/>
			
				<enc:KeyInfo>
			
				  <enc:RetrievalMethod URI="#EK"
			
				   Type="http://www.w3.org/2001/04/xmlenc#EncryptedKey"/>
			
				</enc:KeyInfo>
			
				<enc:CipherData>
			
				  <enc:CipherReference URI="PDF/As You Like It.pdf"/>
			
				</enc:CipherData>
			
			  </enc:EncryptedData>
			
			
			</encryption>
		private var opf:XML = 
			<!DOCTYPE package PUBLIC "+//ISBN 0-9673008-1-9//DTD OEB 1.2 Package//EN"
				"http://openebook.org/dtds/oeb-1.2/oebpkg12.dtd">
			   <package unique-identifier="Package-ID">
				 <metadata>
				   <dc-metadata xmlns:dc="http://purl.org/dc/elements/1.0"
								xmlns:oebpackage="http://openebook.org/namespaces/oeb-package/1.0">
				   <dc:Identifier id="Package-ID">ebook:guid-6B2DF0030656ED9D8</dc:Identifier>
					 <dc:Title>As You Like It</dc:Title>
					 <dc:Creator role="aut">William Shakespeare</dc:Creator>
					 <dc:Identifier>0-7410-1455-6</dc:Identifier>
					 <dc:Subject></dc:Subject>
					 <dc:Type></dc:Type>
				   <dc:Date event="publication">3/24/2000</dc:Date>
				   <dc:Date event="copyright">1/1/9999</dc:Date>
				   <dc:Identifier scheme="ISBN">0-7410-1455-6</dc:Identifier>
					 <dc:Publisher>Project Gutenberg</dc:Publisher>
				   <dc:Language></dc:Language>
				  </dc-metadata>
				 </metadata>
				 <manifest>
				   <item id="4915" href="book.html" media-type="text/x-oeb1-document"/>
				   <item id="7184" href="images/cover.png" media-type="image/png" />
				 </manifest>
				 <spine>
				   <itemref idref="4915"/>
				 </spine>
			   </package>
		private var _ncx:XML = 
		<!DOCTYPE package PUBLIC "+//ISBN 0-9673008-1-9//DTD OEB 1.2 Package//EN"
			"http://openebook.org/dtds/oeb-1.2/oebpkg12.dtd">
		   <package unique-identifier="Package-ID">
			 <metadata>
			   <dc-metadata xmlns:dc="http://purl.org/dc/elements/1.0"
							xmlns:oebpackage="http://openebook.org/namespaces/oeb-package/1.0">
			   <dc:Identifier id="Package-ID">ebook:guid-6B2DF0030656ED9D8</dc:Identifier>
				 <dc:Title>As You Like It</dc:Title>
				 <dc:Creator role="aut">William Shakespeare</dc:Creator>
				 <dc:Identifier>0-7410-1455-6</dc:Identifier>
				 <dc:Subject></dc:Subject>
				 <dc:Type></dc:Type>
			   <dc:Date event="publication">3/24/2000</dc:Date>
			   <dc:Date event="copyright">1/1/9999</dc:Date>
			   <dc:Identifier scheme="ISBN">0-7410-1455-6</dc:Identifier>
				 <dc:Publisher>Project Gutenberg</dc:Publisher>
			   <dc:Language></dc:Language>
			  </dc-metadata>
			 </metadata>
			 <manifest>
			   <item id="4915" href="book.html" media-type="text/x-oeb1-document"/>
			   <item id="7184" href="images/cover.png" media-type="image/png" />
			 </manifest>
			 <spine>
			   <itemref idref="4915"/>
			 </spine>
		   </package>;
			
		public function serialize(data:ByteArray):ByteArray {
			//provide the date for a book
			if (data == null){
				data = new ByteArray();
			}
			if (_zip == null){
				_zip = new ZipFile(_bookname);
			}
			_zip.addTextFile("application/epub+zip","mimetype");
			_zip.addFile(null, "META-INF");
			_zip.addTextFile("META
		}
	}
}
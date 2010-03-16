package ydk.epub.data
{
	import flash.utils.ByteArray;

	public class Opf extends XmlEpubDocument
	{
		public function Opf(buf:ByteArray)
		{
			if (buf == null){
				initialize();
			}else{
				parseXml(buf);
			}
		}
		private static var _fileName:String = "OEBPS/ydkbook.opf";
		
		public static function get fileName():String
		{
			return _fileName;
		}
		
		public function set fileName(value:String):void
		{
			_fileName = value;
		}
		private var _opfName:String = "ydkbook.opf";
		private var _xmlDecl:String = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		protected var _package:XML;
//		= 
//			<package version="2.0" unique-identifier="PrimaryID" xmlns="http://www.idpf.org/2007/opf">
//			</package>;
		protected var _metadata:MetaData;// = new MetaData();
		protected var _manifest:XML;// = <manifest />;
		protected var _spine:XML;// = <spine />;
		protected var _guide:XML;// = <guid />;
		
		public function get xmlDecl():String
		{
			return _xmlDecl;
		}

		public function set xmlDecl(value:String):void
		{
			_xmlDecl = value;
		}

		public function get opfName():String
		{
			return _opfName;
		}

		public function set opfName(value:String):void
		{
			_opfName = value;
		}

		public function assign():void{
			var ns:Namespace = _package.namespace();
			trace("metadata="+_package.ns::metadata[0].toXMLString());
			trace("manifest="+_package.ns::manifest[0].toXMLString());
			_metadata = new MetaData(_package.ns::metadata[0]);
			_manifest = _package.ns::manifest[0];
			_spine = _package.ns::spine[0];
			_guide = _package.ns::guide[0];

		}
//		public function setMetaAtributeNS(node:XML, name:String, value:String, prefix:String):void{
//			//var ns:Namespace = _package.namespace();
//			var meta:XML = _package.ns::metadata[0];
//			var ans:Namespace = new Namespace("opf", "http://www.idpf.org/2007/opf");
//			
//			//var aqname:QName = new QName(ans, name);
//			trace("ans:"+ans);
//			//node.setNamespace(ans);
//			node.@ans::[name] = value;
//			//node.@id="PrimaryID";
//			//trace("qname="+aqname);
//			trace("node.attribute="+node.@ans::[name]);
//			trace("node.attribute="+node.@[name]);
//			trace("node="+node.toXMLString());
//			trace(_package.toXMLString());
//			
//		}
//		public function setDcMetaDatawithNSAttributes(name:String, value:String, prefix:String, attr:String, attrValue:String):XML {
//			var node:XML = _metadata.setDcMetadata(name, value);
//			setMetaAtributeNS(node, attr, attrValue, prefix);
//			return node;
//		}
		public function initialize():void{
			_package = 
				<package version="2.0" unique-identifier="PrimaryID" xmlns="NAMESPACE_TO_BE_REPLACED" >
					<metadata xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:opf="http://www.idpf.org/2007/opf" />
					<manifest />
					<spine toc="ncx" />
				</package>;
			
			assign();
		}
		//15 default metadata by Dublin Core Metadata Element Set, Version 1.1
		// http://www.dublincore.org/documents/dces/
		
//			private var _primaryID:String;
//			private var _uriID:String;
//			private var _description:String;
//			private var _title:String;
//			private var _language:String;
//			private var _creator:String;
//			private var _publisher:String;
//			private var _coverage:String;
//			private var _source:String;
//			private var _publishDate:String;
//			private var _originalPublishDate:String;
//			private var _rights:String;
//			private var _subject:String;
//			private var _type:String;
//			private var _relation:String;
//			private var _format:String;
//			private var _contributor:String;


			public function get contributor() : String
			{
				return _metadata.getDcMetadata("contributor");
			}

			public function set contributor(value:String):void
			{
				_metadata.setDcMetadata("contributor", value);
			}

			public function get format():String
			{
				return _metadata.getDcMetadata("format");
			}

			public function set format(value:String):void
			{
				_metadata.setDcMetadata("format", value);
			}

			public function get relation():String
			{
				return _metadata.getDcMetadata("relation");
			}

			public function set relation(value:String):void
			{
				_metadata.setDcMetadata("relation", value);
			}

			public function get type():String
			{
				return _metadata.getDcMetadata("type");
			}

			public function set type(value:String):void
			{
				_metadata.setDcMetadata("type", value);
			}

			public function get subject():String
			{
				return _metadata.getDcMetadata("subject");
			}

			public function set subject(value:String):void
			{
				_metadata.setDcMetadata("subject", value);
			}

			public function get rights():String
			{
				return _metadata.getDcMetadata("rights");
			}

			public function set rights(value:String):void
			{
				_metadata.setDcMetadata("rights", value);
			}

			public function get originalPublishDate():String
			{
				//<dc:date opf:event="original-publication">1872</dc:date>
				return _metadata.getDcMetadataByAttribute("date", "opf", "event", "original-publication");
			}

			public function set originalPublishDate(value:String):void
			{
				_metadata.setDcMetadataByAttribute("date", value, "opf", "event", "original-publication");
			}

			public function get publishDate():String
			{
				 //<dc:date opf:event="ops-publication">2007-01-16T01:17:34Z</dc:date>
				return _metadata.getDcMetadataByAttribute("date", "opf", "event", "ops-publication");
			}

			public function set publishDate(value:String):void
			{
				_metadata.setDcMetadataByAttribute("date", value, "opf", "event", "ops-publication");
				_metadata.setCustomizeMetadata("ydkpublishdate", value);
			}

			public function get source():String
			{
				return _metadata.getDcMetadata("source");
			}

			public function set source(value:String):void
			{
				_metadata.setDcMetadata("source", value);
			}

			public function get coverage():String
			{
				return _metadata.getDcMetadata("coverage");
			}

			public function set coverage(value:String):void
			{
				_metadata.setDcMetadata("coverage", value);
			}

			public function get publisher():String
			{
				return _metadata.getDcMetadata("publisher");
			}

			public function set publisher(value:String):void
			{
				_metadata.setDcMetadata("publisher", value);
				_metadata.setCustomizeMetadata("ydkpublisher", value);
			}

			public function get creator():String
			{
				return _metadata.getDcMetadataByAttribute("creator",  "opf", "role", "aut");
			}

			public function set creator(value:String):void
			{
				//<dc:creator opf:role="aut" opf:file-as="Verne, Jules">Jules Verne</dc:creator>
				_metadata.setDcMetadataByAttribute("creator", value, "opf", "role", "aut");
			}

			public function get language():String
			{
				return _metadata.getDcMetadata("language");
			}

			public function set language(value:String):void
			{
				_metadata.setDcMetadata("language", value);
			}

			public function get title():String
			{
				return _metadata.getDcMetadata("title");
			}

			public function set title(value:String):void
			{
				_metadata.setDcMetadata("title", value);
			}

			public function get description():String
			{
				return _metadata.getDcMetadata("description");
			}

			public function set description(value:String):void
			{
				_metadata.setDcMetadata("description", value);
			}

			public function get uriID():String
			{
				return _metadata.getDcMetadataByAttribute("identifier", "opf", "scheme", "URI");
			}

			public function set uriID(value:String):void
			{
				//<dc:identifier opf:scheme="URI">http://www.feedbooks.com/book/199</dc:identifier>  
				_metadata.setDcMetadataByAttribute("identifier", value, "opf", "scheme", "URI");
				bookId = value;
			}

			public function get primaryID():String
			{//<dc:identifier id="PrimaryID" opf:scheme="URN">urn:uuid:37d1c550-1bc6-11df-b739-001cc0a62c0b</dc:identifier>
				var nodes:XMLList = _metadata.getDcMetadataByAttribute("identifier", "opf", "scheme", "URN");
				for (var i:String in nodes){
					if (nodes[i].@id== "PrimaryID"){
						return nodes[i];
					}
				}
				return null;
			}

			public function set primaryID(value:String):void
			{
				var node:XML = _metadata.setDcMetadataByAttribute("identifier", "urn:uuid:"+value, "opf", "scheme", "URN");
				node.@id="PrimaryID";
				trace(_package.toXMLString());
			}


			//ydk define metadata
//			private var _bookid:String;
//
//			public function get bookid():String
//			{
//				return _bookid;
//			}
//
//			public function set bookid(value:String):void
//			{
//				_bookid = value;
//			}
			
			
			//start manifest section
			public function addManifestItem(id:String, href:String, mediaType:String) : XML {
				var node:XML = new XML(<item />);
				node.@id=id;
				node.@href=href;
				node.@["media-type"] = mediaType;
				_manifest.appendChild(node);
				return node;
			}
			
			public function addManifestNcx(href:String) : XML {
				return addManifestItem("ncx", href, "application/x-dtbncx+xml");
			}
			
			public function getManifestItem(id:String) : XMLList {
				return _manifest.item.(@id == id);
			} 
			public function addSpineItem(idref:String, linear:String = "yes") : XML {
				var node : XML = new XML (<itemref />);
				node.@idref = idref;
				node.@linear = linear;
				_spine.appendChild(node);
				return node;
			}
			public function addGuideItem(title:String, href:String, type:String = "text") : XML {
				var node : XML = new XML (<reference />);
				node.@type = type;
				node.@title = title;
				node.@href = href;
				_guide.appendChild(node);
				return node;
			}
			
			
			//----------------------------------------------------
			//we define the ydk epub specific meta data to parse into database;
			
			private var _a:String;
			//bookname
			//bookid
			//publisher
			//author
			//traslator
			//format 开本
			//layout 装帧
			//orignalpages
			//paper price
			//isbn
			//coverage 书籍简介
			
			public function get bookName():String
			{
				return _metadata.getCustomizeMetadata("ydkbookname");
			}
			
			public function set bookName(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookname", value);
			}
			public function get bookId():String
			{
				return _metadata.getCustomizeMetadata("ydkbookid");
			}
			
			public function set bookId(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookid", value);
			}
			public function get author():String
			{
				return _metadata.getCustomizeMetadata("ydkauthor");
			}
			
			public function set author(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkauthor", value);
			}
			public function get translator():String
			{
				return _metadata.getCustomizeMetadata("ydktranslator");
			}
			
			public function set translator(value:String):void
			{
				_metadata.setCustomizeMetadata("ydktranslator", value);
			}
			public function get bookFormat():String
			{
				return _metadata.getCustomizeMetadata("ydkbookformat");
			}
			
			public function set bookFormat(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookformat", value);
			}
			public function get bookLayout():String
			{
				return _metadata.getCustomizeMetadata("ydkbooklayout");
			}
			
			public function set bookLayout(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbooklayout", value);
			}
			public function get bookPages():String
			{
				return _metadata.getCustomizeMetadata("ydkbookpages");
			}
			
			public function set bookPages(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookpages", value);
			}
			public function get paperCopyPrice():String
			{
				return _metadata.getCustomizeMetadata("ydkpapercopyprice");
			}
			
			public function set paperCopyPrice(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkpapercopyprice", value);
			}
			public function get bookIsbn():String
			{
				return _metadata.getCustomizeMetadata("ydkbookisbn");
			}
			
			public function set bookIsbn(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookisbn", value);
			}
			
			public function get introduction():String
			{
				return _metadata.getCustomizeMetadata("ydkbookintroduction");
			}
			
			public function set introduction(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookintroduction", value);
			}
			
			public function get thumbnailBig():String
			{
				return _metadata.getCustomizeMetadata("ydkthumbnailbig");
			}
			
			public function set thumbnailBig(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkthumbnailbig", value);
			}
			public function get thumbnailSmall():String
			{
				return _metadata.getCustomizeMetadata("ydkthumbnailsmall");
			}
			
			public function set thumbnailSmall(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkthumbnailsmall", value);
			}
			public function get thumbnail3DBig():String
			{
				return _metadata.getCustomizeMetadata("ydkthumbnail3dbig");
			}
			
			public function set thumbnail3DBig(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkthumbnail3dbig", value);
			}
			public function get thumbnail3DSmall():String
			{
				return _metadata.getCustomizeMetadata("ydkthumbnail3dsmall");
			}
			
			public function set thumbnail3DSmall(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkthumbnail3dsmall", value);
			}
			/*
			public function get bookName():String
			{
				return _metadata.getCustomizeMetadata("ydkbookname");
			}
			
			public function set bookName(value:String):void
			{
				_metadata.setCustomizeMetadata("ydkbookname", value;
			}
			*/
			
			
			
			//---------------------------- process -------------------
			public override function parseXml(buf:ByteArray):void{
				_package =  XML(buf);
				assign();
			}
			public override function serialize ():ByteArray {
				
				var buf:ByteArray = new ByteArray();
				buf.writeUTFBytes(_xmlDecl);
				buf.writeUTFBytes(toXMLString());
				trace("opf="+toXMLString());
				return buf;
			}
			public override function toXMLString():String{
				XML.prettyPrinting = true;
				trace(_package.toXMLString());
				//_package.setNamespace(new Namespace(null, "http://www.idpf.org/2007/opf"));
				var str:String = _package.toXMLString();
				trace("posit:"+str.indexOf("NAMESPACE_TO_BE_REPLACED"));
				str = str.replace("NAMESPACE_TO_BE_REPLACED", "http://www.idpf.org/2007/opf");
				trace(str);
				return str;
			}
	}
}
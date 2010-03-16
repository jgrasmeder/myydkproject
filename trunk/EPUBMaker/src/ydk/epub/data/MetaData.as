package ydk.epub.data
{
/*
	
	The XML namespace mechanism (see http://www.w3.org/TR/REC-xml-names11/) 
	is used to identify the elements used for Dublin Core metadata without conflict. 
	The metadata or dc-metadata  (deprecated) elements may contain any number of instances 
	of any Dublin Core elements. Dublin Core metadata elements may occur in any order; in fact, 
	multiple instances of the same element type (e.g. multiple Dublin Core creator elements) 
	can be interspersed with other metadata elements without change of meaning.
	
	<metadata xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:opf="http://www.idpf.org/2007/opf">
	<dc:title>Tale of Two Cities</dc:title>
	<dc:creator opf:role="aut">Charles Dickens</dc:creator>
	...
	<meta name="price" content="USD 19.99" />
	</metadata>

	*/
	public class MetaData
	{
		public function MetaData(meta:XML)
		{
			_metadata = meta;
			if (_metadata == null){
				_metadata =
					<metadata xmlns:dc="http://purl.org/dc/elements/1.1/"
									xmlns:opf="http://www.idpf.org/2007/opf">
								<dc:title>Missing Title</dc:title>
							   <dc:language>zh-CN</dc:language>
							   <dc:identifier id="BookId" >
								123456789X
							 </dc:identifier>
							   <dc:creator opf:role="aut">YDK aut</dc:creator>
					
								</metadata>;

			}
		}
		
		private var _metadata:XML;
		
		public function get metadata():XML {
			return _metadata;
		}
		public function getMetadata(name:String):XMLList{
			return _metadata.child(name);
		}
		public function setMetadata(name:String, value:String):void{
			var node:XML = new XML("<"+name+" >"+value+"</"+name+">");
			_metadata.appendChild(node);
		}
//		public function get dcTitle (): String{
//			var dc:Namespace = _metadata.namespace("dc");
//	//		default xml namespace = dc;
//			return _metadata.dc::title;
//		}
//		public function set dcTitle (title:String): void{ 
//			var dc:Namespace = _metadata.namespace("dc");
////			default xml namespace = dc;
//			_metadata.dc::["title"] = title;
//			
//		}
		public function setDcMetadata(name:String, value:String):XML {
			var dc:Namespace = _metadata.namespace("dc");
			//			default xml namespace = dc;
//			var node:XML = new XML("<"+name+" >"+value+"</"+name+">");
//			node.setNamespace(dc);
			var node:XML = <{name}> {value}</{name}>;
			node.setNamespace(dc);
			_metadata.appendChild(node);
			return node;
		}
		public function getDcMetadata(name:String):XMLList {
			var dc:Namespace = _metadata.namespace("dc");
			return  _metadata.dc::[name];
		}
		public function setDcMetadataByAttribute(name:String, value:String, prefix:String, attr:String, attrValue:String):XML {
			var dc:Namespace = _metadata.namespace("dc");
			//			default xml namespace = dc;
			var node:XML = <{name} >{value}</{name}>;
			node.setNamespace(dc);
			_metadata.appendChild(node);
			var ns:Namespace = _metadata.namespace(prefix);
			node.@ns::[attr] = attrValue;
			
			trace ("dcMetaAttribute node:"+node.toXMLString());
			trace ("dcMetaAttribute meta:"+_metadata.toXMLString());
			return node;
		}
		public function getDcMetadataByAttribute(name:String,  prefix:String, attr:String, attrValue:String):XMLList {
			var dc:Namespace = _metadata.namespace("dc");
			var ns:Namespace = _metadata.namespace(prefix);
			return  _metadata.dc::[name].(@ns::[attr] == attrValue);
		}
		public function setCustomizeMetadata (name:String, value:String):XML{
			var node:XML = <meta />;
			node.@name = name;
			node.@content = value;
			_metadata.appendChild(node);
			return node;
		}
		public function getCustomizeMetadata (name:String):String{
			var nodes:XMLList = _metadata.child("meta");
			for (var i:String in nodes){
				if (nodes[i].@name == name){
					return nodes[i].@content;
				}
			}
			return null;
		}
	}
}
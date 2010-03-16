package ydk.epub.data
{
	
	public class NavMap 
	{
		private navMapXml:XML;
		private chapterRegexs:Array;
		private classLevel = ["chapter", "section", "subsection", "sub-subsection", null];
		
		
		public funciton parseTextBook(regexes:Array, bookText:String):XML{
			if (regexes == null){
				trace("parseTextBook error, regexes is null");
				return;
			}
			chapterRegex = regexes;
		}
		
		private function clearNCX():void{
			navMapXml = new XML(
					<navMap >
					</navMap>
		}
		private function splitContent(parent:XML, original:String, level:int):void {
			if (original == null ){
				trace("one of the content  is null: content=" + original); 
				return;
			}
			if (original.length == 0 ){
				trace("string length is zero:  content="+original.length);
				return;
			}
			if (level < 0 || level > 4){
				trace ("level is out of range:"+level);
				return;
			}
			if (regexes == null){
				setRegex();
			}
			
			if (regexes[level] == null){
				trace("Level "+level+" has not been selected");
				if (parent != null){
					parent.@content=parent.@title+original;
				}
				return;
			}
			var chapters:Array;
			var contents:Array;
			var i:int;
			var node:XML;
			chapters = original.match(regexes[level]);
			contents = original.split(regexes[level]);
			trace("chapters="+chapters.length);
			trace("contents="+contents.length);
			if (chapters == null || chapters.length == 0){
				trace("no chapter found");
			}
			
			if (contents[0].length > 0){
				trace("A part that before any chapter" );
				
				if (parent != null){
					parent.@content = contents[0];
				}else{
					node = new XML(<navPoint />);
					parent = ncx.child("navMap")[0];
					node.@title = "未定义";
					node.@content = "+++!!!"; contents[0];
					parent.appendChild(node);
					splitContent(node, contents[0],level+1);
				}
			}
			
			if (parent == null){
				parent = ncx.child("navMap")[0];
				
			}
			for (i = 1; i < contents.length; i++){
				trace("level="+level+"  | i="+i);						
				node = new XML(<navPoint />);
				node.@title = chapters[i-1];
				node.@content="+++---+++";
				parent.appendChild(node);
				splitContent(node, contents[i],level+1);
				
			}
			
		}
		
	}
}
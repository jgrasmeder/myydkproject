package ydk.epub.data
{
	import flash.events.Event;
	
	import mx.collections.IViewCursor;
	import mx.collections.Sort;
	
	public class NCX_backup implements INcx
	{
		public function NCX_backup()
		{
			//TODO: implement function
		}
		
		public function parseBook(chapterRegexp:Array, String:*, content:*):Boolean
		{
			//TODO: implement function
			return false;
		}
		
		public function get ncx():XML
		{
			//TODO: implement function
			return null;
		}
		
		public function get length():int
		{
			//TODO: implement function
			return 0;
		}
		
		public function addItem(item:Object):void
		{
			//TODO: implement function
		}
		
		public function addItemAt(item:Object, index:int):void
		{
			//TODO: implement function
		}
		
		public function getItemAt(index:int, prefetch:int=0):Object
		{
			//TODO: implement function
			return null;
		}
		
		public function getItemIndex(item:Object):int
		{
			//TODO: implement function
			return 0;
		}
		
		public function itemUpdated(item:Object, property:Object=null, oldValue:Object=null, newValue:Object=null):void
		{
			//TODO: implement function
		}
		
		public function removeAll():void
		{
			//TODO: implement function
		}
		
		public function removeItemAt(index:int):Object
		{
			//TODO: implement function
			return null;
		}
		
		public function setItemAt(item:Object, index:int):Object
		{
			//TODO: implement function
			return null;
		}
		
		public function toArray():Array
		{
			//TODO: implement function
			return null;
		}
		
		public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void
		{
			//TODO: implement function
		}
		
		public function removeEventListener(type:String, listener:Function, useCapture:Boolean=false):void
		{
			//TODO: implement function
		}
		
		public function dispatchEvent(event:Event):Boolean
		{
			//TODO: implement function
			return false;
		}
		
		public function hasEventListener(type:String):Boolean
		{
			//TODO: implement function
			return false;
		}
		
		public function willTrigger(type:String):Boolean
		{
			//TODO: implement function
			return false;
		}
		
		
		
		public function get filterFunction():Function
		{
			//TODO: implement function
			return null;
		}
		
		public function set filterFunction(value:Function):void
		{
			//TODO: implement function
		}
		
		public function get sort():Sort
		{
			//TODO: implement function
			return null;
		}
		
		public function set sort(value:Sort):void
		{
			//TODO: implement function
		}
		
		public function createCursor():IViewCursor
		{
			//TODO: implement function
			return null;
		}
		
		public function contains(item:Object):Boolean
		{
			//TODO: implement function
			return false;
		}
		
		public function disableAutoUpdate():void
		{
			//TODO: implement function
		}
		
		public function enableAutoUpdate():void
		{
			//TODO: implement function
		}
		
		
		
		public function refresh():Boolean
		{
			//TODO: implement function
			return false;
		}
	}
}
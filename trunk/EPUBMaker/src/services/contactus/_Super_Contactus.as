/**
 * This is a generated class and is not intended for modfication.  To customize behavior
 * of this service wrapper you may modify the generated sub-class of this class - Contactus.as.
 */
package services.contactus
{
import mx.rpc.AsyncToken;
import com.adobe.fiber.core.model_internal;
import mx.rpc.AbstractOperation;
import valueObjects.GetMessages;
import valueObjects.GetMessagesResponse;
import valueObjects.Message;
import valueObjects.PostMessage;
import valueObjects.PostMessageResponse;
import mx.rpc.soap.mxml.WebService;
import mx.rpc.soap.mxml.Operation;
import com.adobe.fiber.services.wrapper.WebServiceWrapper;
import mx.rpc.xml.SchemaTypeRegistry;    

[ExcludeClass]
internal class _Super_Contactus extends WebServiceWrapper
{
     
    // Constructor
    public function _Super_Contactus()
    {
        // initialize service control
         _serviceControl = new WebService();
         SchemaTypeRegistry.getInstance().registerClass(new QName("http://contactus.webservice.ydk.com/", "postMessageResponse"), PostMessageResponse); 
         SchemaTypeRegistry.getInstance().registerClass(new QName("http://contactus.webservice.ydk.com/", "getMessages"), GetMessages); 
         SchemaTypeRegistry.getInstance().registerClass(new QName("http://contactus.webservice.ydk.com", "Message"), Message); 
         SchemaTypeRegistry.getInstance().registerClass(new QName("http://contactus.webservice.ydk.com/", "getMessagesResponse"), GetMessagesResponse); 
         SchemaTypeRegistry.getInstance().registerClass(new QName("http://contactus.webservice.ydk.com/", "postMessage"), PostMessage); 
         var operations:Object = new Object();
         var operation:Operation;         
         
         operation = new Operation(null, "getMessages");
		 operation.resultType = valueObjects.GetMessagesResponse; 		 
         operations["getMessages"] = operation;
    
         operation = new Operation(null, "postMessage");
		 operation.resultType = valueObjects.PostMessageResponse; 		 
         operations["postMessage"] = operation;
    
         _serviceControl.operations = operations;              

  

         _serviceControl.service = "ContactUsServiceImplService";
         _serviceControl.port = "ContactUsServiceImplPort";
         _serviceControl.wsdl = "http://221.122.79.226:8080/MyYdkProject/webservice/contactus?wsdl";
         _serviceControl.loadWSDL();
      
     
         model_internal::initialize();
    }

	/**
	  * This method is a generated wrapper used to call the 'getMessages' operation. It returns an AsyncToken whose 
	  * result property will be populated with the result of the operation when the server response is received. 
	  * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
	  * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an AsyncToken whose result property will be populated with the result of the operation when the server response is received.
	  */          
	public function getMessages(getMessages:valueObjects.GetMessages) : AsyncToken
	{
		model_internal::loadWSDLIfNecessary();
		var _internal_operation:AbstractOperation = _serviceControl.getOperation("getMessages");
		var _internal_token:AsyncToken = _internal_operation.send(getMessages) ;

		return _internal_token;
	}   
	 
	/**
	  * This method is a generated wrapper used to call the 'postMessage' operation. It returns an AsyncToken whose 
	  * result property will be populated with the result of the operation when the server response is received. 
	  * To use this result from MXML code, define a CallResponder component and assign its token property to this method's return value. 
	  * You can then bind to CallResponder.lastResult or listen for the CallResponder.result or fault events.
      *
      * @see mx.rpc.AsyncToken
      * @see mx.rpc.CallResponder 
      *
      * @return an AsyncToken whose result property will be populated with the result of the operation when the server response is received.
	  */          
	public function postMessage(postMessage:valueObjects.PostMessage) : AsyncToken
	{
		model_internal::loadWSDLIfNecessary();
		var _internal_operation:AbstractOperation = _serviceControl.getOperation("postMessage");
		var _internal_token:AsyncToken = _internal_operation.send(postMessage) ;

		return _internal_token;
	}   
	 
}

}

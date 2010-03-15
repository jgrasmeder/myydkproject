/**
 * 
 */
package com.ydk.webservice.contactus;

import java.util.List;
import java.util.ArrayList;

import javax.jws.WebService;
/**
 * @author y140zhan
 *
 */
@WebService(endpointInterface = "com.ydk.webservice.contactus.ContactUsService")
public final class ContactUsServiceImpl implements ContactUsService {

    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(
                "Willie", "Wheeler", "willie.wheeler@xyz.com", "Great job"));
        messages.add(new Message(
                "Dardy", "Chen", "dardy.chen@xyz.com", "I want my money back"));
        return messages;
    }

    public void postMessage(Message message) {
        System.out.println(message);
    }
}

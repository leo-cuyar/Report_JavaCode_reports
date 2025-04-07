/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;


import Email.SendEmail;
import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "WebServices.FarmerMarket")
public class FarmerMarketImpl implements FarmerMarket{

    @Override
    public String sendPasswordResetEmail(String emailAddress, String reset_code) {
        SendEmail send=new SendEmail();        
        return send.sendPasswrodReset(emailAddress, reset_code);
    }
    
}

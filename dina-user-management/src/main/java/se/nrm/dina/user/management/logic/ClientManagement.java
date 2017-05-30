/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.user.management.logic;

import java.io.Serializable; 
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder; 
import org.keycloak.representations.idm.ClientRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.nrm.dina.user.management.json.JsonConverter;
import se.nrm.dina.user.management.utils.CommonString;

/**
 *
 * @author idali
 */ 
public class ClientManagement implements Serializable {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  
    private Keycloak keycloakClient;
    
    @Inject
    public JsonConverter json;
    
    public ClientManagement() {
        
    }
    
    public JsonObject getAllTheClients() {
        buildRealm();
        List<ClientRepresentation> clientsRepresetation = keycloakClient.realm(CommonString.getInstance().getDinaRealm()).clients().findAll();
 
        keycloakClient.close();
        return json.converterClients(clientsRepresetation); 
    }
         
    private void buildRealm() {   
        keycloakClient = KeycloakBuilder.builder()
                                        .serverUrl(CommonString.getInstance().getKeyCloakLUrl()) //
                                        .realm(CommonString.getInstance().getMastRealm())//
                                        .username(CommonString.getInstance().getMasterAdminUsrname()) //
                                        .password(CommonString.getInstance().getMasterAdminPassword()) //
                                        .clientId(CommonString.getInstance().getAdminClientId())
                                        .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()) //
                                        .build(); 
    }
}

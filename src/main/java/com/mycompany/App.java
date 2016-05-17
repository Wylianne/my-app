package com.mycompany;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.json.JSONArray;
import org.json.JSONObject;


/**
* @author Wylianne Costa
* Aplicação para inserção e consulta de contatos
* Atualiazação:
*   - Métodos retornam em JSONObject ou JSONArray.
*   - Documentação todos os métodos com  @autor, @version, @return e @param (Quando necessário).
* V. 1.02
*/
public class App extends Jooby {
	private ArrayList<Agenda> contacts = new ArrayList<>();
        
        private int ultimoId = 1;

	{
        
            /**
            * @author Wylianne Costa
            * @version 1.00
            * @return String - Welcome
            * Método para retornar o "Welcome" ao usuário.
            * É executado quando nada é passado na URL
            */
            get("/", ()  -> "Welcome");
            
            
            
            /**   
            * @author Wylianne Costa
            * @version 1.02
            * @return JSONObject - Dados do Contato: id, nome e telefone
            * Método para retornar um contato.
            * É executado quando /todos é passado na URL junto a um parametro.   
            * @param id int - id do contato.
            */
            get("/todos/:id", req ->{
                
                int id = Integer.parseInt(req.param("id").value());
                String json_str = "{}";
                int statusCode = 404;
                

                if (contacts.size() > 0) {
                    for (Agenda contact : contacts) {
                        statusCode = 200;
                        if (contact.getId() == id){
                            json_str = "{\"ID\":"+ contact.getId() +",\"Name\":\""+ contact.getName() +"\",\"Phone\":\""+ contact.getPhone()+"\"}";
                        }
                    } 
                }

                JSONObject jsonObj = new JSONObject(json_str);
	        return jsonObj;
	    });
            
            

            /**
            * @author Wylianne Costa
            * @version 1.01
            * @return JSONArray - Dados de todos os contatos cadastrados: id, nome e telefone
            * Método para retornar todos os cadastros ao usuário.
            * É executado quando /todos é passado na URL.            
            */
            get("/todos", () ->{
                int statusCode = 404;
                String json_str = "{}";
                
                if (contacts.size() > 0) {
                    json_str = "";
                    for (Agenda contact : contacts) {
                        statusCode = 200;
                        if (json_str != ""){
                            json_str = json_str + ",";
                        }

                        json_str = json_str + "{\"ID\":"+ contact.getId() +",\"Name\":\""+ contact.getName() +"\",\"Phone\":\""+ contact.getPhone()+"\"}";
                    } 
                    
                }
                json_str = "["+json_str+"]";
                JSONArray jsonArr =  new  JSONArray (json_str);
	        return jsonArr;
	    });

            /**
            * @author Wylianne Costa
            * @version 1.01
            * @return JSONObject - Dados do contato cadastrado: id, nome e telefone
            * Método para cadastrar um novo contato.
            * É executado quando dados são passados via post e /todos é passado na URL.
            * @param name String - Nome do contato.
            * @param phone String - Número do contato.
            */
            post("/contact", req -> {
                ObjectMapper mapper = new ObjectMapper();

                String jsonInString = req.body().value();
                Agenda contact = mapper.readValue(jsonInString, Agenda.class);
                
                int statusCode = 400;
                String message;

                if (contact.getName().equals("")) {
                    message = "{\"message\": \"Nome não pode ser vazio.\"}";
                } else if (contact.getPhone().equals("")) {
                    message = "{\"message\": \"O número de telefone não pode ser vazio.\"}";
                } else {
                    statusCode = 200;
                    contact.setId(this.ultimoId);
                    this.ultimoId++;
                    this.contacts.add(contact);
                    message = mapper.writeValueAsString(contact);
                }
                
                JSONObject jsonObj = new JSONObject(message);
                
                return jsonObj;
            });
            
            
  
        }


  public static void main(final String[] args) throws Throwable {
    run(App::new, args);
    
  }

}


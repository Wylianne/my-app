package com.mycompany;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.Results;
/**
* @author: Wylianne Costa
* Aplicação para inserção e consulta de contatos
* Atualiazação:
*   - Método para retornar um contato por id.
*   - Comentários sobre os parametros via post.
*   - Comentários da classe Agenda.
* V. 1.1
*/
public class App extends Jooby {
	private ArrayList<Agenda> contacts = new ArrayList<>();
        
        private int ultimoId = 1;

	{
            /**
            * Método para retornar o "Welcome" ao usuário.
            * É executado quando nada é passado na URL.
            */
            get("/", () -> "Welcome!");
            
            
            /**
            * Método para retornar um contato.
            * É executado quando /todos é passado na URL junto a um parametro.   
            * @param id int - id do contato.
            */
            get("/todos/:id", req ->{
                
                int id = Integer.parseInt(req.param("id").value());
                String message = "";
                int statusCode = 404;
                boolean find = false;
                
                System.out.println("-----   Pesquisa de Contato   -----");

                if (contacts.size() > 0) {
                    for (Agenda contact : contacts) {
                        statusCode = 200;
                        if (contact.getId() == id){
                            find = true;
                            message = message+ "ID: "+ contact.getId() +"Nome: " + contact.getName() + ",  Telefone:" + contact.getPhone();
                            message = message + "\n";
                            message = message + "\n";
                        }
                    } 
                }
                
                if (!find){
                    message = "Contato não localizado!";
                }

	        return Results.with(message).status(statusCode).type("text/plain");
	    });
            
            

            /**
            * Método para retornar todos os cadastros ao usuário.
            * É executado quando /todos é passado na URL.
            */
            get("/todos", () ->{

                String message = "";
                int statusCode = 404;
                System.out.println("-----   Lista de Contatos   -----");

                if (contacts.size() > 0) {
                    for (Agenda contact : contacts) {
                        statusCode = 200;
                        message = message+ "ID: "+ contact.getId() +"Nome: " + contact.getName() + ",  Telefone:" + contact.getPhone();
                        message = message + "\n";
                        message = message + "\n";
                    } 
                }else{
                        statusCode = 200;
                        message = "Lista vazia!";

                }

	        return Results.with(message).status(statusCode).type("text/plain");
	    });
	
            
            
            /**
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
                    message = "Nome não pode ser vazio.";
                } else if (contact.getPhone().equals("")) {
                    message = "O número de telefone não pode ser vazio.";
                } else {
                    statusCode = 200;
                    contact.setId(this.ultimoId);
                    this.ultimoId++;
                    this.contacts.add(contact);
                    message = mapper.writeValueAsString(contact);
                }

                return Results.with(message).status(statusCode).type("text/plain");
            });
        }


  public static void main(final String[] args) throws Throwable {
    run(App::new, args);
    
  }

}


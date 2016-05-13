package com.mycompany;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.Results;
/**
* @author: Wylianne Costa
* Aplicação para inserção e consulta de contatos
* V. 1.0
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
            * Método para retornar todos os cadastros ao usuário.
            * É executado quando /todos é passado na URL.
            */
            get("/todos", () ->{

                String message = "";
                String result = "";
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
            * Dados necessários para o cadastro: Nome e Telefone.
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


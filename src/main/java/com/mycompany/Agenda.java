/*
 * Copyright 2016 NoteSamsung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @author: Wylianne Costa
* V. 1.0
*/


public class Agenda {
    
    int id;
    String name;
    String phone;
    
    //Instaciando a classe
    public Agenda(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    Agenda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        //Métodos de GETs e SETs da classe
	public int getId() {
		return id;
	}
        
        public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setId(int id) {
		this.id = id;
	}
        
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
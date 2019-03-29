/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufs.poo.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Luiz Souza
 */    

public class validaEmail {
	
	public boolean validar(String email) {
		
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^(?:[\\w_+.]+)@[\\w_]+(?:.\\w{2}(?:\\w+)?)(?:.\\w{2})?$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
	
}

   
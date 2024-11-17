/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Usuario {
    
    int id;
    String userName;
    String password;
    String mail;
    int idRol;

    public Usuario(int id, String userName, String password, String mail, int idRol) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;        
        this.idRol = idRol;        
    }
    
    public Usuario(String userName, String password, String mail, int idRol) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;        
        this.idRol = idRol;        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }  

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
        
}

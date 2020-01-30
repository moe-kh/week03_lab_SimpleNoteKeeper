/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.io.Serializable;

/**
 *
 * @author lpeters
 */
public class Note implements Serializable {
     private String title;
     private String content;
     
     public Note(){
         
     }
     public Note(String title, String content){
         this.content=content;
         this.title=title;
         
     }

    @Override
    public String toString() {
        return "Note{" + "title=" + title + ", content=" + content + '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

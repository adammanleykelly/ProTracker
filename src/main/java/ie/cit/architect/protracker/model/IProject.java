package ie.cit.architect.protracker.model;

import java.util.Date;

/**
 * Created by brian on 23/03/17.
 */
public interface IProject {

   String getLocation();

   void setLocation(String location);

   String getName();

   void setName(String name);

   Date getDate();

   void setDate(Date date);

   String getAuthor();

   void setAuthor(String author);

   String getClientName();

   void setClientName(String clientName);

   int getProjectId();

   void setProjectId(int projectId);



}

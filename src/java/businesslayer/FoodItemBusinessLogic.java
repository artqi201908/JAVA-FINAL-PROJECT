/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.FoodItemDAOImpl;
import transferobject.FoodItemDTO;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author phron
 */
public class FoodItemBusinessLogic {
  private FoodItemDAOImpl foodItemDAO = null;
	public FoodItemBusinessLogic(){
            foodItemDAO= new FoodItemDAOImpl();
        }
  
	 public  List<FoodItemDTO> getAllInventory(int userID) throws SQLExceptionList {
	        return foodItemDAO.getAllFoodItems();
	    }
}  

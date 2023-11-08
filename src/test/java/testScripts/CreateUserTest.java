package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericlibraries.BaseClass;
import genericlibraries.IconstantPath;

public class CreateUserTest extends BaseClass {
	@Test
	
	public void createUserTest() {
		SoftAssert soft = new SoftAssert();
	home.clickUsersTab();
	soft.assertTrue(users.getPageHeader().contains("Users"));
	
	
	users.clickNewButton();
	soft.assertEquals(addUser.getPageHeader(), "Add New User");
	Map<String,String> map = excel.readFromExcel("Sheet1", "Add User");
	addUser.createNewUser(map.get("Email"), map.get("Password"), map.get("First Name"), map.get("Last Name"),
			map.get("Address"), map.get("Contact Info"), map.get("Photo"));
	
	soft.assertTrue(users.getSuccessMessage().contains("Success"));
	
	if(course.getSuccessMessage().contains("Success"))
		excel.writeToExcel("Sheet1", "Add User", "Pass", IconstantPath.EXCEL_PATH);
	else
		excel.writeToExcel("Sheet1", "Add User", "Fail", IconstantPath.EXCEL_PATH);
	soft.assertAll();
	
	
	
	
	

}
}

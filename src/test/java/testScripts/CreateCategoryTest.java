package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericlibraries.BaseClass;
import genericlibraries.IconstantPath;

public class CreateCategoryTest extends BaseClass {
	
	
	@Test
	public void createCategoryTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		home.clickCoursersTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		Map<String, String> map = excel.readFromExcel("Sheet1", "Add Category");
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		String categoryName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(categoryName);
		addCategory.clickSave();
		
		
		soft.assertTrue(category.getSuccessMessage().contains("Sucess"));
		boolean isPresent = false;
		List<WebElement>categoryList = category.getCategoryList();
		for(WebElement e : categoryList) {
			if(e.getText().equals(categoryName)) {
				isPresent = true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		
		category.clickDeleteButton(categoryName, driver);
		category.clickDelete();
		soft.assertTrue(category.getSuccessMessage().contains("Sucess"));
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet1", "Add Course", "Pass", IconstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet1", "Add Course", "Fail", IconstantPath.EXCEL_PATH);
		
		soft.assertAll();
		category.clickNewButton();
		
		addCategory.setName(map.get("Name"));
		addCategory.clickSave();
		
		Assert.assertTrue(category.getSuccessMessage().contains("Success"));
		
		//List<WebElement> categoryList = category.getCategoryList();
		category.clickDeleteButton(categoryName, driver);
		category.clickDelete();
		
		
	}

}

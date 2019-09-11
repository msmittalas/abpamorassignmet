<html>
<head>
<script src="jscript/jquery-3.4.1.min.js"></script>
<script src="jscript/jquery.serializeToJSON.js"></script>

<script>
function getSingleRecipe(id,url)
{
	$.ajax(url+id,{
		type:'GET',
		success : function(response,status,xhr){
			
			response=JSON.parse(response);
			fillEditForm(response.data);
		},
		error : function(response,status,xhr){}
	});
}


function fillEditForm(data)
{
	data=data[0];
	/*
	 recipeName: <input name="recipeName" type="text"  id="recipeName" />
<br/>  VEG/NON-VEG :  <input name="isVeg" type="text" value="veg" id="isVeg"/> 
 <br/>numberOfPeople: <input name="numberOfPeople" type="text" id="numberOfPeople" />
 <br/>ingredients: <input name="ingredients" type="text" id="ingredients" />
  <br/>instructions: <input name="instructions" type="textarea" id="instructions" />
	*/
	$("#editRecipeForm #recipeName").val(data.recipeName);
	$("#editRecipeForm #isVeg").val(data.isVeg);
	$("#editRecipeForm #numberOfPeople").val(data.numberOfPeople);
	$("#editRecipeForm #ingredients").val(data.ingredients);
	$("#editRecipeForm #instructions").val(data.instructions);

	
	$("#editViewRecipe").show();
	
}

function buildFromResponse(data)
{
	var output="";
	 for (var i = 0; i < data.length; i++)
	 {
         output += "<option value='"+data[i].recipeId+"'> <p> "  + data[i].recipeName + "</p>" +"</option>";
     }
	 return output;
}

function getAllRecipe(url)
{
	$("#listRecipes").hide();
	
	$.ajax(url+"all",
			{
		    type:'GET',
		    success: function(response,status,xhr){
		    	
		    	response=JSON.parse(response);
		    	
			var dataToDisplay=buildFromResponse(response.data);	 
			if(response.data.length>0)
			{
				$("#listRecipes").find("option").remove();
			    $("#listRecipes").append(dataToDisplay);
			    $("#listRecipes").show();
					
			}	
			
		    },
		    error: function(data,status,xhr){
		    	
		    	$('#message').append('Error: ' + errorMessage);
		    },
		    
			});
	
	}
$(document).ready(function(){
	   var restURL="http://localhost:8080/assignment/rest/recipes/";
	getAllRecipe(restURL);
	$("#manageRecipe").click(function(){
		
		 var selectedVal = $(this).find(':selected').val();
		    var selectedText = $(this).find(':selected').text();
		    getSingleRecipe(selectedVal,restURL);
		    
	});
	
  $("#submitAdd").click(function(){
	  var recipeObject=$("#addRecipeForm").serializeToJSON({});
	  var jsonData=JSON.stringify(recipeObject);

	  $.ajax(restURL+"add",{
		  dataType: 'json',
          contentType: 'application/json',
			type: 'POST',  // http method
			data:jsonData , 
			success: function (response, status, xhr) {
				
				alert(response.status.toUpperCase()=="SUCCESS");
				if(response.status.toUpperCase()=="SUCCESS")
					{
					$('#message').html("");
					
					$('#message').append('New Recipe Added ').show().delay(2000).fadeOut();
					
						getAllRecipe(restURL);
					}
			},
			error: function (jqXhr, textStatus, errorMessage) {
					$('#message').append('Error: ' + errorMessage);
				}	
	  }
	  	);
	  
  });
});
</script>
</head>
<body>
<h2>Dashboard</h2>
<h3 id="message"></h3>
<div >
Select Recipe and Press Manage to View/Delete/Edit 
<select id="listRecipes">
</select>

<input value="manage" type="button" id="manageRecipe" /> 
</div>

<div id="createRecipesDiv">
create Recipes 

<form action="addRecipe" method="post" id="addRecipeForm">
<br/>
 recipeName: <input name="recipeName" type="text" />
<br/> VEG/NON-VEG :  <input name="isVeg" type="text" value="veg" /> 
 <br/>numberOfPeople: <input name="numberOfPeople" type="text" />
 <br/>ingredients: <input name="ingredients" type="text" />
  <br/>instructions: <input name="instructions" type="textarea" />
  <br/><input value ="submit" type="button" id="submitAdd" /> 
  <br/><input value ="RESET" type="reset" /> 
  <input value="add" name="recipeAction" type="hidden" />
  
</form>

</div>

 <div id="editViewRecipe"  style="display:none">
 <form  method="post" id="editRecipeForm">
<br/>
<h3>Edit Recipe </h3>
 recipeName: <input name="recipeName" type="text"  id="recipeName" />
<br/>  VEG/NON-VEG :  <input name="isVeg" type="text" value="veg" id="isVeg"/> 
 <br/>numberOfPeople: <input name="numberOfPeople" type="text" id="numberOfPeople" />
 <br/>ingredients: <input name="ingredients" type="text" id="ingredients" />
  <br/>instructions: <input name="instructions" type="textarea" id="instructions" />
  <br/><input value ="Edit" type="button" id="submitEdit" /> 
  <br/><input value ="RESET" type="reset" /> 
  
</form>
 </div>




</body>
</html>

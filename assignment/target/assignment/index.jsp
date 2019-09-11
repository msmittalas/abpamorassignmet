<html>
<head>
<script src="jscript/jquery-3.4.1.min"></script>
<script src="jscript/jquery.serializeToJSON.js"></script>

<script>
$(document).ready(function(){
  $("#submitAdd").click(function(){
   
	  console.log($("#addRecipeForm").serializeToJSON({}));
	  
  });
});
</script>
</head>
<body>
<h2>Dashboard</h2>
<div>

<ul>
<li>
</li>
</ul>
</div>

<div>
create Recipes 

<form action="addRecipe" method="post" id="addRecipeForm">
<br/>
 recipeName: <input name="recipeName" type="text" />
<br/>  VEG <input name="isVeg" type="radio" value="v" />  OR NON VEG <input name="isVeg" type="radio" value="n" /> 
 <br/>numberOfPeople: <input name="numberOfPeople" type="text" />
 <br/>ingredients: <input name="ingredients" type="text" />
  <br/>instructions: <input name="instructions" type="textarea" />
  <br/><input value ="submit" type="button" id="submitAdd" /> 
  <br/><input value ="RESET" type="reset" /> 
  <input value="add" name="recipeAction" type="hidden" />
  
</form>

<form>
FORM
</form>
</div>






</body>
</html>

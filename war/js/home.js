$(document).ready(function(){

});

facebookCallBack = function(authorized)	{
	if (authorized)
		location.reload(1);
	else
		alert('Ocorreu um erro no login, tente novamente!');
};
function setbg_p(e, color, reminder)
{

	e.style.background=color;
	if (e.value == "")
	{
		e.value = reminder;
		e.onfocus = function(){clear_input_p(e, '#e5fff3')};
	}
return true;
}


function setbg(id, color, reminder)
{

	document.getElementById(id).style.background=color;
	if (document.getElementById(id).value == "")
	{
		document.getElementById(id).value = reminder;
		document.getElementById(id).onfocus = function(){clear_input(id, '#e5fff3')};
	}
return true;
}

function clear_form(form_id)
{ 
	form = document.getElementById(form_id);
    for (i=0; i<form.length; i++)
    {
        if (form.elements[i].type == "text" || form.elements[i].type == "textarea"
            || form.elements[i].type =="password")
        {
        	form.elements[i].value = "";
        }
    }
}


function clear_input_p(e, color) 
{ 
    e.value = ""; 
    e.style.background=color;
    e.onfocus = null; 
} 

function clear_input(id, color) 
{ 
    document.getElementById(id).value = ""; 
    document.getElementById(id).style.background=color;
    document.getElementById(id).onfocus = null; 
} 

function check_input_p(e, color) 
{ 
    if(e.value == "")
    {
    	e.onfocus = function(){clear_input_p(e, '#e5fff3')};
    } 
    else
    {
        e.onfocus = null; 
    }
    
} 

function check_input(id, color) 
{ 
    if(document.getElementById(id).value == "")
    {
    	document.getElementById(id).onfocus = function(){clear_input(id, '#e5fff3')};
    } 
    else
    {
        document.getElementById(id).onfocus = null; 
    }
    
} 

function checkAll(form_p, checkbox_name, control_p){

	var checkValue = control_p.checked;
	for (j=0; j< form_p.length; j++)
	{
		if (form_p.elements[j].name == (checkbox_name))
		{
			form_p.elements[j].checked = checkValue;
		}
	}
}
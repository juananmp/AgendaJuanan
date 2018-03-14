for $x in doc("Agenda.xml")/Agenda/Persona
return if ($x/@name="")
then <name>{data($x/name)}</name>
else <name>{data($x/name)}</name>

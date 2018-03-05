for $x in doc("Agenda.xml")/Agenda/Persona
return if ($x/@categoria="Children")
then <child>{data($x/name)}</child>
else <adult>{data($x/name)}</adult>

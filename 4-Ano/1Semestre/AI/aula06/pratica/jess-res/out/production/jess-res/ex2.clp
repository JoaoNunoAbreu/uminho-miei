(deftemplate person
	(slot hair-color)
	(multislot name)
	(slot eye-color)
	(slot age)
	(slot abstraction))

(deffacts persons
    (person
        (hair-color black)
        (eye-color blue)
        (name Joao Marsupilami Silva)
        (age 23))
    (person
        (hair-color brown)
        (eye-color blue)
        (name Gonçalo Rodrigues Conceição)
        (age 38))
    (person
        (hair-color red)
        (eye-color brown)
        (name António Carvalho Deus)
        (age 20))
    (person
        (hair-color blond)
        (eye-color green)
        (name Ronaldo Martins Pereira)
        (age 88))
    (person
        (hair-color brown)
        (eye-color black)
        (name Francisco dos Santos Pereira)
        (age 2)))

;; -------------------------- Rules --------------------------

(defrule rule1
	(person (name $? ?s) (age ?i))
	=>
	(printout t "Person with surname " ?s " has " ?i " years." crlf))

(defrule rule2
	(person (name $? ?s) {(hair-color == brown) || (hair-color == black)})
	=>
	(printout t "Person with surname " ?s " has dark hair." crlf))


(defrule rule3
	?p <- (person {(eye-color == green || eye-color == blue)})
	=>
	(printout t ?p.name " has " ?p.eye-color " eyes." crlf))

(defrule rule4
	?p <- (person {(eye-color != green || eye-color != blue)})
	=>
	(printout t ?p.name " has neither blue nor green eyes". crlf))

(defrule rule5
	(person (age ?i))
	=>
	(if(exists (person {age == ?i}))
	then (printout t "There isn't anyone with the same age!". crlf)
	else (printout t "There are persons with the same age!". crlf)))


(defrule rule6
	(exists (person {eye-color == blue}))
	=>
	(printout t "There's at least one person who has blue eyes!" crlf))

(defrule rule7.1
	?p <- (person {age <= 12})
	=>
	(modify ?p (abstraction child)))

(defrule rule7.2
	?p <- (person {age >= 13 && age < 65})
	=>
	(modify ?p (abstraction adult)))

(defrule rule7.3
	?p <- (person {age >= 65})
	=>
	(modify ?p (abstraction third-age)))

(defrule rule8
	(not (exists (person {(abstraction == child || abstraction == third-age)})))
	=>
	(printout t "Every person is an adult!"))

(defrule rule9
	?p <- (person (age ?i))
	=>
	(if(<= ?i 12) then (modify ?p (abstraction child))
	else (if (and (>= ?i 13) (< ?i 65)) then (modify ?p (abstraction adult))
									else (modify ?p (abstraction third-age)))))
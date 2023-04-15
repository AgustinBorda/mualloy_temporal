var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
	all p : Protected | p not in Trash' and p.^link not in Trash'
}

run  prop9_faulty
var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
  	
	
  	always (some link.File implies eventually link.File in Trash)	
  	
}

run  prop8_faulty
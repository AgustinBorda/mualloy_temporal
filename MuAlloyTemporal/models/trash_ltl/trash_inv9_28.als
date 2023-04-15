var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
	
  	 always (Protected in Protected)
}

run  prop9_faulty
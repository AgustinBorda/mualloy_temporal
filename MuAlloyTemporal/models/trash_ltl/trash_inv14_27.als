var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	
  	always (Protected in Trash implies no Protected')
}

run  prop14_faulty
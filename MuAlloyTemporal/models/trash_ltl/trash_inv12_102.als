var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
  eventually all t : Trash | after t not in Trash
}

run  prop12_faulty
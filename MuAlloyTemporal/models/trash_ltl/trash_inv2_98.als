var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop2_faulty { 
  some File and no Trash and no Protected
}

run  prop2_faulty
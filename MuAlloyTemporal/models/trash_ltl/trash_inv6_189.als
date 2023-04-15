var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop6_faulty { 
  all t: Trash | t in Trash triggered t in Trash
}

run  prop6_faulty
var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
  always all p: Protected | eventually p in Trash implies p not in Protected
}

run  prop18_faulty
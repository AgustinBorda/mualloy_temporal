var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some f: File | eventually f in Trash implies f not in Protected

}

run  prop4_faulty
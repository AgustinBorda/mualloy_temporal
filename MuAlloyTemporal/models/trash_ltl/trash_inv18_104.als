var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
    always Trash not in Protected & Protected'
}

run  prop18_faulty
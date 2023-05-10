import React, { useState, Fragment } from 'react'
import AddUserForm from './forms/AddUserForm'
import EditUserForm from './forms/EditUserForm'
import UserTable from './tables/UserTable'
import axios from 'axios'
const host = 'http://localhost:8080/'

const App = () => {
	
	// Data
	// const usersData = [
	// 	{ id: 1, name: 'Tania', description: 'floppydiskette' },
	// 	{ id: 2, name: 'Craig', description: 'siliconeidolon' },
	// 	{ id: 3, name: 'Ben', description: 'benisphere' },
	// ]
	let usersData = []

	const initialFormState = { id: null, name: '', description: '' }

	// Setting state
	const [users, setUsers] = useState(usersData)
	const [currentUser, setCurrentUser] = useState(initialFormState)

	const [editing, setEditing] = useState(false)

	React.useEffect(()=>{
		viewData()
	},[])

	function viewData(){
		axios.get(host + 'subjects').then((response) => {
			setUsers([])
			console.log(users)
			for(let i = 0; i < response.data.length; i++){
				// usersData.push(response.data[i])
				usersData.unshift(response.data[i])
				// setUsers([response.data[i], ...users])
			}
			console.log(users)
			// setUsers([usersData, ...users])
			setUsers(usersData)
			console.log(users)
			console.log("view--------")
		}).catch(function (error) {
			console.log(error)
		})
	}

	function createData(user){
		axios.post(host + 'subjects', {name:user.name,description:user.description})
		.then((response) => {
			//setUsers(response.data);
			viewData()
		  })

	}

	function updataData(id, updatedUser){
		axios.put(host + 'subjects/' + id, {name:updatedUser.name,description:updatedUser.description}).then()
	}

	function deleteData(id){
		axios.delete(host + 'subjects/' + id).then()
	}


	const addUser = user => {
		// user.id = users.length + 1
		// setUsers([user, ...users])
		createData(user)
	}

	const deleteUser = id => {
		setEditing(false)
		console.log(id+"=delete")
		deleteData(id)
		setUsers(users.filter(user => user.id !== id))
	}

	const updateUser = (id, updatedUser) => {
		setEditing(false)
		updataData(id, updatedUser)
		setUsers(users.map(user => (user.id === id ? updatedUser : user)))
	}

	const editRow = user => {
		setEditing(true)
		setCurrentUser({ id: user.id, name: user.name, description: user.description })
	}


	return (
		<div className="container">
			<h1>Hello React</h1>
			<div className="flex-row">
				<div className="flex-large">
					{editing ? (
						<Fragment>
							<h2>Edit user</h2>
							<EditUserForm
								editing={editing}
								setEditing={setEditing}
								currentUser={currentUser}
								updateUser={updateUser}
							/>
						</Fragment>
					) : (
						<Fragment>
							<h2>Add user</h2>
							<AddUserForm addUser={addUser} />
						</Fragment>
					)}
				</div>
				<div className="flex-large">
					<h2>View users</h2>
					<UserTable users={users} editRow={editRow} deleteUser={deleteUser} />
				</div>
			</div>
		</div>
	)
}

export default App

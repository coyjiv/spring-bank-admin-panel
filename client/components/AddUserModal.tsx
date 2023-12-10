import { Customer, SERVER_URL } from "@/pages/users";
import { Dialog, Transition } from "@headlessui/react";
import { Fragment, useState } from "react";

type Props = {
    open: boolean;
    handleClose: () => void;
    handleUpdate: (customer: Customer) => void;
}

const AddUserModal = ({open, handleClose, handleUpdate}: Props) => {
    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [age, setAge] = useState<string | number>("")
    const [isPending, setIsPending] = useState(false)

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value)
    }

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value)
    }

    const handleAgeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setAge(parseInt(e.target.value))
    }

    const submit = (e: React.MouseEvent) => {
        e.preventDefault()
        setIsPending(true)
        const data = {
            name,
            email,
            age,
        }
        fetch(SERVER_URL + '/customers/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(data => {
                setIsPending(false)
                console.log(data);
                    handleUpdate(data)
                    setName("")
                    setEmail("")
                    setAge("")
                handleClose()
            })
    }
  return (
    <Transition.Root show={open} as={Fragment}>
            <Dialog as="div" className="relative z-10" onClose={handleClose}>
                <Transition.Child
                    as={Fragment}
                    enter="ease-out duration-300"
                    enterFrom="opacity-0"
                    enterTo="opacity-100"
                    leave="ease-in duration-200"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0"
                >
                    <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
                </Transition.Child>

                <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
                    <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                        <Transition.Child
                            as={Fragment}
                            enter="ease-out duration-300"
                            enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                            enterTo="opacity-100 translate-y-0 sm:scale-100"
                            leave="ease-in duration-200"
                            leaveFrom="opacity-100 translate-y-0 sm:scale-100"
                            leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                        >
                            <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white px-4 pb-4 pt-5 text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-sm sm:p-6">
                                <div>
                                    <div className="mt-3 text-center sm:mt-5">
                                        <Dialog.Title as="h3" className="text-base font-semibold leading-6 text-gray-900">
                                            Create a new customer
                                        </Dialog.Title>
                                        <form className="mt-2 space-y-5">
                                            <div>
                                                <label htmlFor="email" className="block text-left text-sm font-medium leading-6 text-gray-900">
                                                    Email
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        type="email"
                                                        name="email"
                                                        id="email"
                                                        value={email}
                                                        onChange={handleEmailChange}
                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 disabled:cursor-not-allowed disabled:bg-gray-50 disabled:text-gray-500 disabled:ring-gray-200 sm:text-sm sm:leading-6"
                                                        placeholder="you@example.com"
                                                    />
                                                </div>
                                            </div>
                                            <div>
                                                <label htmlFor="email" className="block text-left text-sm font-medium leading-6 text-gray-900">
                                                    Name
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        type="text"
                                                        name="name"
                                                        id="name"
                                                        value={name}
                                                        onChange={handleNameChange}
                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 disabled:cursor-not-allowed disabled:bg-gray-50 disabled:text-gray-500 disabled:ring-gray-200 sm:text-sm sm:leading-6"
                                                        placeholder="John Doe"
                                                    />
                                                </div>
                                            </div>
                                            <div>
                                                <label htmlFor="email" className="block text-left text-sm font-medium leading-6 text-gray-900">
                                                    Age
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        type="number"
                                                        name="age"
                                                        id="age"
                                                        value={age}
                                                        onChange={handleAgeChange}
                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 disabled:cursor-not-allowed disabled:bg-gray-50 disabled:text-gray-500 disabled:ring-gray-200 sm:text-sm sm:leading-6"
                                                        placeholder="32"
                                                    />
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div className="mt-5 sm:mt-6 flex gap-2">
                                    <button
                                        type="button"
                                        className="inline-flex w-full justify-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                        onClick={handleClose}
                                    >
                                        Close
                                    </button>
                                    <button
                                        onClick={submit}
                                        type="button"
                                        className="inline-flex w-full justify-center rounded-md bg-purple-400 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-bg-purple-400"
                                    >
                                        {isPending? 
                                        <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                        <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                                        <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4z"></path>
                                    </svg>
                                        : "Save & Close"}
                                    </button>
                                </div>
                            </Dialog.Panel>
                        </Transition.Child>
                    </div>
                </div>
            </Dialog>
        </Transition.Root>
  )
}

export default AddUserModal
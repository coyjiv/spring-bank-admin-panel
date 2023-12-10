import { Customer, SERVER_URL } from '@/pages/users';
import { Dialog, Transition } from '@headlessui/react';
import React, { Fragment, useMemo } from 'react'

type Props = {
    open: boolean;
    handleClose: () => void;
    handleUpdate: (customer: Customer) => void;
    customers: Customer[];
}

const RemoveUserModal = ({ open, handleClose: handleCloseParent, handleUpdate, customers }: Props) => {
    const [ search, setSearch ] = React.useState("")
    const [ isPending, setIsPending ] = React.useState(false)
    const [ selectedCustomer, setSelectedCustomer ] = React.useState<Customer | null>(null)

    const selectCustomer = (customer: Customer) => {
        setSelectedCustomer(customer)
    }

    const handleClose = () => {
        setSelectedCustomer(null)
        handleCloseParent()
    }

    const filteredCustomers = useMemo(() => {
        return customers.filter(customer => {
            return customer.name.toLowerCase().includes(search.toLowerCase())
        })
    }, [ search, customers ])

    const customersToChoose = search.length > 0 ? filteredCustomers : customers

    const rendableCustomers = customersToChoose.length === 0 ? <h1>No results...</h1> : customersToChoose.map(customer => {
        return (
            <div key={customer.id} onClick={() => selectCustomer(customer)} className={`cursor-pointer hover:bg-gray-100 rounded-md px-4 py-2 ${selectedCustomer?.id === customer.id ? 'bg-gray-200 hover:bg-gray-200' : ''} transition-all `}>
                <div className="flex items-center justify-between">
                    <div className="flex items-center">
                        <div className="flex items-start flex-col">
                            <p className="text-sm font-medium text-gray-900">
                                {customer.name}
                            </p>
                            <p className="text-sm text-gray-500">
                                {customer.email}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        )
    })


    const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearch(e.target.value)
    }



    const submit = (e: React.MouseEvent) => {
        e.preventDefault()
        setIsPending(true)
        fetch(SERVER_URL + '/customers/delete/' + selectedCustomer?.id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(data => {
                console.log(data);
                setIsPending(false)
                handleUpdate(selectedCustomer as Customer)
                setSearch("")
                setSelectedCustomer(null)
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
                                            Remove a customer
                                        </Dialog.Title>
                                        <form className="mt-2 space-y-5">
                                            <div>
                                                <label htmlFor="email" className="block text-left text-sm font-medium leading-6 text-gray-900">
                                                    Search
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        type="text"
                                                        name="search"
                                                        id="search"
                                                        value={search}
                                                        onChange={handleSearchChange}
                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 disabled:cursor-not-allowed disabled:bg-gray-50 disabled:text-gray-500 disabled:ring-gray-200 sm:text-sm sm:leading-6"
                                                        placeholder="you@example.com"
                                                    />
                                                </div>
                                            </div>
                                            <div className='flex flex-col overflow-y-auto max-h-96'>
                                                {rendableCustomers}
                                            </div>

                                        </form>
                                    </div>
                                </div>
                                <div className="mt-5 sm:mt-6 flex gap-2">
                                    <button
                                        type="button"
                                        className="inline-flex w-full justify-center rounded-md bg-green-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-green-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
                                        onClick={handleClose}
                                    >
                                        Discard
                                    </button>
                                    <button
                                        onClick={submit}
                                        type="button"
                                        disabled={!selectedCustomer}
                                        className="disabled:opacity-25 inline-flex w-full justify-center rounded-md bg-purple-400 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-purple-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-bg-purple-400"
                                    >
                                        {isPending ?
                                            //spinner
                                            <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                                                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4z"></path>
                                            </svg>
                                            : "Delete"}
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

export default RemoveUserModal
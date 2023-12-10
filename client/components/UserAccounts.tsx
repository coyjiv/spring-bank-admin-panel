import { Account } from "@/pages/users";
import NextLink from "next/link";

export const UserAccounts = ({ accounts }: { accounts: Account[] }) => (
    <div className='border rounded-2xl px-5 py-5 mb-5'>
      <p className='font-medium'>User Accounts : </p>
      <div className='flex flex-col gap-2 mt-4'>
        {accounts.length > 0 ? accounts.map((account: Account, index) => (
          <div key={account.id}>
            <div className="flex justify-start gap-5 hover:bg-slate-400/10 text-gray-500 p-2 rounded-xl">
              <p>Account identifier: {account.number}</p>
              <span>|</span>
              <p>balance: {account.currency} {account.balance}</p>
              <NextLink href={'/accounts/'+account.number} className="ml-auto mr-3 text-indigo-600 text-sm font-medium hover:text-indigo-900">
                Details<span className="sr-only">Details</span>
              </NextLink>
            </div>
            {index!==accounts.length-1 && <div className='separator w-full h-[1px] bg-slate-400/10'></div>}
          </div>
        ))
          : <p className='text-gray-500'>No accounts</p>
        }
      </div>
    </div>
  )
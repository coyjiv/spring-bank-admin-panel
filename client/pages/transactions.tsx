import Layout from '@/components/layout'
import React from 'react'

type Props = {}

const Transactions = (props: Props) => {
  return (
    <div>Transactions</div>
  )
}

Transactions.getLayout = function getLayout(page: React.ReactElement) {
    return <Layout>{page}</Layout>
}

export default Transactions
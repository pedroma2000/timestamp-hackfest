import './App.css';
import { useState } from 'react';
import { BsFillBoxSeamFill } from 'react-icons/bs'

import { productList } from './template_files/productlist'

function App() {

  const [search_input, setInput] = useState("")
  const input_change = newVal => {
    setInput(newVal.target.value)
  }

  const filtered_products = productList.filter((product) =>
    product.title.toLowerCase().includes(search_input.toLowerCase())
  )

  const addProduct = () => {
    alert('Produto adicionado')
  }

  return (
    <div className="app">
      <div className='app-header'>
        <BsFillBoxSeamFill className='inventory-icon' />
        <div>Inventory Manager</div>
      </div>
      <div className='app-button-box'>
        <input className='text-input' 
        type="text"
        placeholder='Pesquisar...'
        value={search_input}
        onChange={ input_change }
        />
        <button className='mButton create'
        onClick={addProduct}
        > + Add Product </button>
      </div>
      <div className='app-product-list'>
        {
          filtered_products.length ? 
            filtered_products.map((product) => 
              <div className='product-row'> 
                <div className='product-uuid'> 
                  <div className='product-label'>
                    ID:
                  </div>
                  <div className='product-value'>
                    {product.uuid}
                  </div>
                </div> 
                <div className='product-title'>
                  <div className='product-label'>
                    Title:
                  </div>
                  <div className='product-value'>
                    {product.title}
                  </div>
                </div>
                <div className='product-cost'>
                  <div className='product-label'>
                  Cost:
                  </div>
                  <div className='product-value'>
                    {product.cost}
                  </div>
                </div> 
              </div>
            )
          : <div className='emptyList-label'> We cannot find any products</div>
        }
      </div>
    </div>
  );
}

export default App;

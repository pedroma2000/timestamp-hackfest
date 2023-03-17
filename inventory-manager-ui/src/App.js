import './App.css';
import { useState } from 'react';
import { BsFillBoxSeamFill } from 'react-icons/bs'
import { IoClose } from 'react-icons/io5'
import { Popup } from 'reactjs-popup'
import axios from 'axios'

import { productList } from './template_files/productlist'

function App() {

  const [search_input, setInput] = useState("")
  const [product_name_input, setProductName] = useState("")
  const [product_cost_input, setProductCost] = useState("")
  const [addPopup, setAddPopup] = useState(false)
  const input_change = newVal => {
    setInput(newVal.target.value)
  }

  const pName_change = newVal => {
    setProductName(newVal.target.value)
  }

  const pCost_change = newVal => {
    setProductCost(newVal.target.value)
  }

  const filtered_products = productList.filter((product) =>
    product.title.toLowerCase().includes(search_input.toLowerCase())
  )

  const addProduct = () => {
    setAddPopup(true);
  }

  const postProduct = () => {

    axios
      .post("http://localhost:8080/product", {
        title: product_name_input,
        cost: product_cost_input
      }, {
        headers: {
          'Content-type': 'application/json'
        }
      })
      .then((response) => {
        alert("Produto adicionado:\n" + response.data)
        setAddPopup(false)
        setProductName("")
        setProductCost("")
      })
      .catch((err) => console.log(err));
  }

  return (
    <>
      <Popup open={addPopup}>
        <div className='add-popup bg'>
          <div className='add-popup body'>
            <div className='add-popup bar'> 
              <div className='add-popup title'> ADD PRODUCT </div>
              <IoClose className='add-popup close' onClick={() => setAddPopup(false)} />
            </div>
            <div className='add-popup form'>
                <input className='text-input productName' 
                type="text"
                placeholder='Name'
                value={product_name_input}
                onChange={ pName_change }
                />
                <input className='text-input productCost' 
                type="float"
                placeholder='Cost'
                value={product_cost_input}
                onChange={ pCost_change }
                />
            </div>
            <button className='mButton popup'
                onClick={postProduct}
                > Add Product </button>
          </div>
        </div>
      </Popup>
      <div className="app">
      <div className='app-header'>
        <BsFillBoxSeamFill className='inventory-icon' />
        <div>Inventory Manager</div>
      </div>
      <div className='app-button-box'>
        <input className='text-input' 
        type="text"
        placeholder='Search...'
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
    </>
  );
}

export default App;

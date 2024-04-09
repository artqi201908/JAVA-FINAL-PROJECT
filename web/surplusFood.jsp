<%-- 
    Document   : surplusFood
    Created on : Apr 8, 2024, 9:33:53 PM
    Author     : phron
--%>

<%@page import="java.util.List"%>
<%@page import="transferobject.FoodItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Surplus Food Management</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

        <div class="sidebar">
            <!-- Sidebar links as in your retailer.jsp -->
        </div>

        <div class="content">
            <h2>Surplus Food Management</h2>
            <p>Items nearing expiration or in excess of demand. Mark items for donation or sale at a discounted price.</p>
            <table>
                <tr>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Expiration Date</th>
                    <th>Discount Rate</th>
                    <th>Action</th> <!-- Additional column for actions like flagging for donation/sale -->
                </tr>
                <%
                    List<FoodItemDTO> foodListSurplus = (List<FoodItemDTO>) request.getAttribute("foodListSurplus");
                    if (foodListSurplus != null && !foodListSurplus.isEmpty()) {
                        for (FoodItemDTO food : foodListSurplus) {
                %>
                <tr>
                    <td><%= food.getName()%></td>
                    <td><%= food.getQuantity()%></td>
                    <td><%= food.getPrice()%></td>
                    <td><%= food.getExpirationDate()%></td>
                    <td><%= food.getDiscountRate().setScale(2)%></td>
                    <td>
                        <form action="FlagForDonationServlet" method="post">
                            <input type="hidden" name="itemId" value="<%= food.getItemID()%>">
                            <button type="submit">Flag for Donation</button>
                        </form>
                        <form action="ApplyDiscountServlet" method="post">
                            <input type="hidden" name="itemId" value="<%= food.getItemID()%>">
                            <input type="text" name="discountRate" placeholder="New Discount Rate">
                            <button type="submit">Apply Discount</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" style="text-align:center;">No surplus food items found.</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>

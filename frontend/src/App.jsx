import { Toaster } from "sonner";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider, useAuth } from "./contexts/AuthContext";
import HomePage from "./pages/HomePage.jsx";
import MovieDetail from "./pages/MovieDetail.jsx";
import SearchPage from "./pages/SearchPage.jsx";
import AuthPage from "./pages/AuthPage.jsx";
import NotFound from "./pages/NotFound.jsx";
import WatchMovie from "./pages/WatchMovie.jsx";
import ListPage from "./pages/ListPage.jsx";
import AdminPage from "./pages/AdminPage.jsx";
import DashboardPage from "./pages/admin/DashboardPage.jsx";
import FilmsManagement from "./pages/admin/FilmsManagement.jsx";
import UsersManagement from "./pages/admin/UsersManagement.jsx";
import UserDetailPage from "./pages/admin/UserDetailPage.jsx";
import PlansManagement from "./pages/admin/PlansManagement.jsx";
import CommentsManagement from "./pages/admin/CommentsManagement.jsx";
import ReviewsManagement from "./pages/admin/ReviewsManagement.jsx";
import ReportsManagement from "./pages/admin/ReportsManagement.jsx";
import SubscriptionPage from "./pages/SubscriptionPage.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";
import FavouritePage from "./pages/FavouritePage.jsx";
import PurchaseHistoryPage from "./pages/PurchaseHistoryPage.jsx";
import HistoryPage from "./pages/HistoryPage.jsx";
import { useEffect } from "react";
import { keycloak } from "./keycloak.js";

function AppContent() {
  const { login } = useAuth();

  useEffect(() => {
    const initializeKeycloak = async () => {
      try {
        const authenticated = await keycloak.init({ onLoad: 'check-sso', silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html' });
        if (authenticated) {
          const profile = await keycloak.loadUserProfile();
          login(profile, keycloak.token);
        }
      } catch (error) {
        console.error("Keycloak initialization failed", error);
      }
    };
    initializeKeycloak();
  }, [login]);

  return (
    <>
      <Toaster
        richColors
        position="top-right"
        toastOptions={{
          style: {
            marginTop: '80px',
          },
        }}
      />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/film/:id" element={<MovieDetail />} />
          <Route path="/watch/:id" element={<WatchMovie />} />
          <Route path="/search/:type" element={<ListPage key={window.location.pathname + window.location.search} />} />
          <Route path="/subscription" element={<SubscriptionPage />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/favorites" element={<FavouritePage />} />
          <Route path="/history" element={<HistoryPage />} />
          <Route path="/purchase-history" element={<PurchaseHistoryPage />} />
          <Route path="/auth" element={<AuthPage />} />

          {/* Admin Routes */}
          <Route path="/admin" element={<AdminPage />}>
            <Route index element={<DashboardPage />} />
            <Route path="films" element={<FilmsManagement />} />
            <Route path="users" element={<UsersManagement />} />
            <Route path="users/:id" element={<UserDetailPage />} />
            <Route path="plans" element={<PlansManagement />} />
            <Route path="comments" element={<CommentsManagement />} />
            <Route path="reviews" element={<ReviewsManagement />} />
            <Route path="reports" element={<ReportsManagement />} />
          </Route>

          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;
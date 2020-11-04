package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CommentDao;
import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.daos.RecommendDao;
import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.ProductImage;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.services.RecommendService;
import com.toan.ecommercedemo.utils.Constants;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RecommendDao recommendDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    DataSource dataSource;

    @Override
    public List<ShortProductDto> getRecommendation(Long userId) {
        ConnectionPoolDataSource connectionPoolDataSource = new ConnectionPoolDataSource(dataSource);
        DataModel dataModel = new MySQLJDBCDataModel(
                connectionPoolDataSource, "comment", "customer_id",
                "product_id", "rating", null);
        try {
//            ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
//            GenericItemSimilarity genericItemSimilarity = new GenericItemSimilarity(itemSimilarity, dataModel);
//            Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood =
                    new NearestNUserNeighborhood(2, similarity, dataModel);
            Recommender recommender =
                    new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            List<RecommendedItem> recommendedItems = recommender.recommend(userId, 10);
            List<ShortProductDto> dtos = new ArrayList<>();
            for (RecommendedItem recommendedItem : recommendedItems) {
                Product entity = productDao.getById(recommendedItem.getItemID());
                ShortProductDto dto = modelMapper.map(entity, ShortProductDto.class);
                List<ProductImage> imageEntities = entity.getImages();
                if (imageEntities.size() > 0) {
                    ProductImage imageEntity = imageEntities.get(0);
                    if (imageEntity.getFromTiki())
                        dto.setImage(imageEntity.getPath());
                    else {
                        dto.setImage(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
                    }
                }
                List<Comment> comments = entity.getComments();
                if (comments.size() > 0) {
                    dto.setTotalComment(comments.size());
                    double sumRating = 0;
                    for (Comment comment : comments) {
                        sumRating += comment.getRating();
                    }
                    dto.setRating((double) Math.round(sumRating / comments.size() * 10) / 10);
                } else {
                    dto.setRating(0);
                    dto.setTotalComment(0);
                }
                dtos.add(dto);
            }
            return dtos;
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShortProductDto> getRecommendationOfCustomer(Long userId, String path) {
        DataModel dataModel = null;
        try {
            dataModel = new FileDataModel(new File(path));
//            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//            UserNeighborhood neighborhood =
//                    new NearestNUserNeighborhood(2, similarity, dataModel);
//            Recommender recommender =
//                    new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
            Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            List<RecommendedItem> recommendedItems = recommender.recommend(userId, 10);
            List<ShortProductDto> dtos = new ArrayList<>();
            for (RecommendedItem recommendedItem : recommendedItems) {
                Product entity = productDao.getById(recommendedItem.getItemID());
                ShortProductDto dto = modelMapper.map(entity, ShortProductDto.class);
                List<ProductImage> imageEntities = entity.getImages();
                if (imageEntities.size() > 0) {
                    ProductImage imageEntity = imageEntities.get(0);
                    if (imageEntity.getFromTiki())
                        dto.setImage(imageEntity.getPath());
                    else {
                        dto.setImage(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
                    }
                }
                List<Comment> comments = entity.getComments();
                if (comments.size() > 0) {
                    dto.setTotalComment(comments.size());
                    double sumRating = 0;
                    for (Comment comment : comments) {
                        sumRating += comment.getRating();
                    }
                    dto.setRating((double) Math.round(sumRating / comments.size() * 10) / 10);
                } else {
                    dto.setRating(0);
                    dto.setTotalComment(0);
                }
                dtos.add(dto);
            }
            return dtos;
        } catch (TasteException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getRecommendationInMovieLens(Long userId, String path) {
        DataModel dataModel = null;
        try {
            dataModel = new FileDataModel(new File(path));
//            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//            UserNeighborhood neighborhood =
//                    new NearestNUserNeighborhood(2, similarity, dataModel);
//            Recommender recommender =
//                    new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
            Recommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            List<RecommendedItem> recommendedItems = recommender.recommend(userId, 10);
            List<Long> longs = new ArrayList<>();
            for (RecommendedItem recommendedItem : recommendedItems) {
                longs.add(recommendedItem.getItemID());
            }
            return longs;
        } catch (
                IOException | TasteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createData() {
        writeCSV();
    }

    public void writeCSV() {
        List<Comment> comments = commentDao.getAll();
        try {
            FileWriter myWriter = new FileWriter("/home/harpuiasaber/dataComment.csv");
            for (Comment comment : comments) {
                myWriter.write(comment.getCustomer().getId() + "," + comment.getProduct().getId() + "," + (double) comment.getRating());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
